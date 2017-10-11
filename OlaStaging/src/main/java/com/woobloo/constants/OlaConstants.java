package com.woobloo.constants;

public interface OlaConstants {

	String OLA_URL = "https://sandbox-t1.olacabs.com/v1";
	String ACCESS_TOKEN = "access_token";
	String SCOPE = "scope";
	String State = "state";
	String TOKEN_TYPE = "token_type";
	String EXPIRES_IN = "expires_in";

	String REDIRECT_FOR_TOKEN = "<html><head>" + "<script type=\"text/javascript\">" + "function cc()" + "{"
			+ "var xmlHttp = new XMLHttpRequest();  xmlHttp.open( 'GET', window.location.href.replace('#', '?'), false ); xmlHttp.send( null ); return xmlHttp.responseText;     "
			+ "}" + "</script>" + "</head>" + "<body onload='cc()'>" + "hello</body>" + "</html>";

	String HTTP_GET = "GET";
	String HTTP_POST = "POST";
	// Access token
	String AUTHORIZATION = "Authorization";
	String X_APP_TOKEN = "X-APP-TOKEN";
	String X_APP_TOKEN_VALUE = "75780b81a26048398f230996e5baa492";

	String CONTENT_TYPE = "Content-Type";

	String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	// Request Action Header

	String REQUEST_ACTION = "Action";

	// Pick UP
	String PICKUP_LAT = "pickup_lat";
	String PICKUP_LNG = "pickup_lng";

	// Drop at
	String DROP_LAT = "drop_lat";
	String DROP_LNG = "drop_lng";

	// Booking id
	String BOOKING_ID = "booking_id";
	String CATEGORY = "category";
	String PICKUP_MODE = "pickup_mode";
	String SEATS = "seats";

	// Resources uri
	String BOOK_URI = "/bookings/create";
	String GETALLCABS_URI = "/products";
	String CANCEL_RIDE_URI = "/bookings/cancel";
	String CANCELLATION_REASONS_URI = "/bookings/cancel/reasons";
	String TRACK_CAB_URI = "/bookings/track_ride";

	

}
