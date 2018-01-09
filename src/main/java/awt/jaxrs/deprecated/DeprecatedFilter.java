package awt.jaxrs.deprecated;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.container.*;

import org.slf4j.*;

import com.typesafe.config.*;

public class DeprecatedFilter implements ContainerResponseFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeprecatedFilter.class);

    private final ResourceInfo info;

    public DeprecatedFilter(final ResourceInfo info) {
	this.info = info;
    }

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext)
	    throws IOException {
	final String clazz = this.info.getResourceClass().getSimpleName();
	final String method = this.info.getResourceMethod().getName();
	LOGGER.warn("Deprecated endpoint being called: {}#{}", clazz, method);

	final URI base = requestContext.getUriInfo().getBaseUri();
	final Config config = ConfigFactory.load();

	responseContext.getHeaders().putSingle(config.getString("httpheader.deprecated"),
		String.format("299 %s \"Deprecated API\"", base.getHost()));
    }
}