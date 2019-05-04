#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.models.request;

public class IOSFLookupRequest {

    private String lookupKey;

    public IOSFLookupRequest() {
    }

    public IOSFLookupRequest(String lookupKey) {
        this.lookupKey = lookupKey;
    }

    public String getLookupKey() {
        return lookupKey;
    }

    public void setLookupKey(String lookupKey) {
        this.lookupKey = lookupKey;
    }
}
