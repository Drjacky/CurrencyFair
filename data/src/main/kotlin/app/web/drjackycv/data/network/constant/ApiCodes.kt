package app.web.drjackycv.data.network.constant

object ApiCodes {
    const val TOO_MANY_TAGS = 1 /*Too many tags in ALL query
    When performing an 'all tags' search, you may not specify more than 20 tags to join together.*/
    const val UNKNOWN_USER = 2 /*Unknown user
    A user_id was passed which did not match a valid flickr user.*/
    const val PARAMETERLESS_DISABLED = 3 /*Parameterless searches have been disabled
    To perform a search with no parameters (to get the latest public photos, please use flickr.photos.getRecent instead).*/
    const val NO_PERMISSION_POOL = 4 /*You don't have permission to view this pool
    The logged in user (if any) does not have permission to view the pool for this group.*/
    const val USER_DELETED = 5 /*User deleted
    The user id passed did not match a Flickr user.*/
    const val API_NOT_AVAILABLE = 10 /*Sorry, the Flickr search API is not currently available.
    The Flickr API search databases are temporarily unavailable.*/
    const val TAG_INVALID = 11 /*No valid machine tags
    The query styntax for the machine_tags argument did not validate.*/
    const val EXCEEDED_MAX_TAGS = 12 /*Exceeded maximum allowable machine tags
    The maximum number of machine tags in a single query was exceeded.*/
    const val ONLY_SEARCH_YOUR_CONTACTS = 17 /*You can only search within your own contacts
    The call tried to use the contacts parameter with no user ID or a user ID other than that of the authenticated user.*/
    const val ILLOGICAL_ARGUMENTS = 18 /*Illogical arguments
    The request contained contradictory arguments.*/
    const val INVALID_API_KEY = 100 /*Invalid API Key
    The API key passed was not valid or has expired.*/
    const val SERVICE_UNAVAILABLE = 105 /*Service currently unavailable
    The requested service is temporarily unavailable.*/
    const val WRITE_OPERATION_FAILED = 106 /*Write operation failed
    The requested operation failed due to a temporary issue.*/
    const val FORMAT_NOT_FOUND = 111 /*Format "xxx" not found
    The requested response format was not found.*/
    const val METHOD_NOT_FOUND = 112 /*Method "xxx" not found
    The requested method was not found.*/
    const val INVALID_SOAP = 114 /*Invalid SOAP envelope
    The SOAP envelope send in the request could not be parsed.*/
    const val INVALID_XML = 115 /*Invalid XML-RPC Method Call
    The XML-RPC request document could not be parsed.*/
    const val BAD_URL = 116 /*Bad URL found
    One or more arguments contained a URL that has been used for abuse on Flickr.*/
}