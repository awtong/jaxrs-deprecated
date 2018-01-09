package awt.jaxrs.deprecated;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.*;

import javax.ws.rs.container.*;
import javax.ws.rs.core.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeprecatedFilterTest {
    @Mock
    private ResourceInfo info;

    @Mock
    private ContainerRequestContext request;

    @Mock
    private ContainerResponseContext response;

    @Mock
    private Method method;

    @Mock
    private UriInfo uriInfo;

    @Mock
    private MultivaluedMap<String, Object> map;

    @Test
    public void testFilter() throws IOException, URISyntaxException {
	when(this.method.getName()).thenReturn("methodName");
	when(this.info.getResourceMethod()).thenReturn(this.method);
	doReturn(DeprecatedFilterTest.class).when(this.info).getResourceClass();
	when(this.request.getUriInfo()).thenReturn(this.uriInfo);
	when(this.uriInfo.getBaseUri()).thenReturn(new URI("sampletest.com"));
	when(this.response.getHeaders()).thenReturn(this.map);

	final DeprecatedFilter filter = new DeprecatedFilter(this.info);
	filter.filter(this.request, this.response);

	verify(this.map, times(1)).putSingle(any(), any());
    }
}
