package com.woobloo.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.woobloo.contants.TicketConstants;
import com.woobloo.contants.UtilityConstants;
import com.woobloo.apigatewayproxy.model.ApiGatewayProxyResponse;
import com.woobloo.apigatewayproxy.model.ApiGatewayRequest;
import com.woobloo.database.DBManager;
import com.woobloo.input.model.NewTicket;
import com.woobloo.response.model.SuccessMessage;
import com.woobloo.response.model.SuccessStatus;
import com.woobloo.response.model.Ticket;
import com.woobloo.response.model.TicketType;
import com.woobloo.utility.Utility;

public class AgentTicketServices {

	public static ApiGatewayProxyResponse createNewTicket(LambdaLogger lambdaLogger, ApiGatewayRequest gatewayRequest) {
		ApiGatewayProxyResponse response = null;
		DBManager dManager = null;
		SuccessMessage successMessage = new SuccessMessage();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		lambdaLogger.log("lat lang body is: " + gatewayRequest.getBody());
		try {
			NewTicket newTicket = Utility.jsonToObjcet(gatewayRequest.getBody(), NewTicket.class);
			lambdaLogger.log("lat lang  is: " + newTicket.getLatitude() + "  " + newTicket.getLongitute());
			StringBuffer append = new StringBuffer();

			append.append("INSERT INTO TICKET_DETAILS");
			append.append(
					"(`ID_CUSTOMER`,`CTKT_STATUS`,`ATKT_STATUS`,`TICKET_TYPE`,`TKT_CATEGORY`,`ID_LATITUDE`,`ID_LONGITUDE`,`TM_STAMP`,`CREATED_BY`,`MODIFIED_BY` )");
			append.append("VALUES(?,'R','N',?,?,?,?,now(),?,?)");

			dManager = new DBManager();
			con = dManager.getDBConnection();
			stmt = con.prepareStatement(append.toString(), Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, newTicket.getSubscriberId());
			stmt.setString(2, newTicket.getServiceType());
			stmt.setString(3, newTicket.getServiceCategory());
			stmt.setString(4, newTicket.getLatitude());
			stmt.setString(5, newTicket.getLongitute());
			stmt.setString(6, "Self");
			stmt.setString(7, "Myself");

			stmt.executeUpdate();
			successMessage = new SuccessMessage();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				successMessage.setTicketId(Integer.toString(rs.getInt(1)));

			}

			successMessage.setMessage("Ticket posted successfully.");
			successMessage.setStatus(true);
			successMessage.setAgentAvailable(false);

			response = new ApiGatewayProxyResponse(200, UtilityConstants.responseHeaders,
					Utility.objectToJsonString(successMessage));
			lambdaLogger.log(response.getBody());

		} catch (Exception e) {
			Utility.logExceptionStack(e, lambdaLogger);
		} finally {
			DBManager.endSQLActivity(rs, stmt, con);
		}
		return response;
	}

	public static ApiGatewayProxyResponse updateTicketStatus(LambdaLogger lambdaLogger,
			ApiGatewayRequest gatewayRequest) {

		ApiGatewayProxyResponse response = null;
		DBManager dManager = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			StringBuffer append = new StringBuffer();

			append.append("UPDATE `TICKET_DETAILS` ");
			append.append("set `ATKT_STATUS`=?  where `ID_TICKET`=?");

			dManager = new DBManager();
			con = dManager.getDBConnection();
			stmt = con.prepareStatement(append.toString());
			stmt.setString(1, UtilityConstants.changeToStatus
					.get(gatewayRequest.getPathParameters().get(TicketConstants.TICKETS_ACTION)));
			stmt.setInt(2, Integer.parseInt(gatewayRequest.getPathParameters().get(TicketConstants.TICKET_ID)));

			stmt.executeUpdate();

			SuccessStatus successStatus = new SuccessStatus();
			successStatus.setMessage("Ticket updated successfully.");
			successStatus.setStatus("SUCCESS");
			successStatus.setCode(200);
			response = new ApiGatewayProxyResponse(200, UtilityConstants.responseHeaders,
					Utility.objectToJsonString(successStatus));
			lambdaLogger.log(response.getBody());

		} catch (Exception e) {
			Utility.logExceptionStack(e, lambdaLogger);
		} finally {
			DBManager.endSQLActivity(rs, stmt, con);
		}
		return response;

	}

	public static ApiGatewayProxyResponse getNewTicketsDetails(LambdaLogger lambdaLogger, String ticketType)
			throws Exception {
		ArrayList<Ticket> list = new ArrayList<Ticket>();
		DBManager dManager = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ApiGatewayProxyResponse response = null;
		StringBuffer append = new StringBuffer();
		try {
			append.append(
					" SELECT A.ID_TICKET AS IDTICKET, C.ID_CUSTOMER AS IDCUSTOMER, C.NM_FIRST AS NMFIRST, C.NM_LAST AS NMLAST, ");
			append.append(
					" A.TKT_CATEGORY AS TKTCATEGORY, A.ID_LATITUDE AS IDLATITUDE, A.ID_LONGITUDE AS IDLONGITUDE,CAT.TX_DESCRIPTION AS TXDESCRIPTION,TIME_FORMAT(TIMEDIFF(now(),A.TM_STAMP),'%H:%i') as TIME_LAPSE");
			append.append(" FROM TICKET_DETAILS A JOIN TICKETS_CATEGORY CAT ON A.TKT_CATEGORY=CAT.CD_CODE ");
			append.append(" INNER JOIN CUST_DETAILS C ON A.ID_CUSTOMER = C.ID_CUSTOMER ");
			if (ticketType == null || ticketType.equalsIgnoreCase("")) {
				append.append("");
			} else {
				append.append(" WHERE ATKT_STATUS='" + ticketType + "' ");
			}
			append.append(" ORDER BY A.TM_STAMP DESC ");
			dManager = new DBManager();
			con = dManager.getDBConnection();
			stmt = con.prepareStatement(append.toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Ticket ticket = new Ticket();
				ticket.setTicketId(rs.getInt("IDTICKET"));
				ticket.setSubscriberId(rs.getString("IDCUSTOMER"));
				ticket.setSubscriberName(rs.getString("NMLAST"));
				ticket.setCategory(rs.getString("TKTCATEGORY"));
				ticket.setPickup_lat(rs.getString("IDLATITUDE"));
				ticket.setPickup_lng(rs.getString("IDLONGITUDE"));
				ticket.setCategoryDescription(rs.getString("TXDESCRIPTION"));
				ticket.setTimeLapse(rs.getString("TIME_LAPSE"));

				list.add(ticket);
			}
			TicketType ticket = new TicketType();
			ticket.setTicket(list);
			String body = Utility.objectToJsonString(ticket);

			response = new ApiGatewayProxyResponse(200, UtilityConstants.responseHeaders, body);
			lambdaLogger.log(response.getBody());
			DBManager.endSQLActivity(rs, stmt, con);
		} catch (Exception e) {
			Utility.logExceptionStack(e, lambdaLogger);
		} finally {

		}
		return response;
	}

}