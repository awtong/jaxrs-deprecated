package awt.jaxrs.deprecated;

import java.lang.reflect.Method;

import javax.ws.rs.container.*;
import javax.ws.rs.core.FeatureContext;

public class DeprecatedFeature implements DynamicFeature {

    @Override
    public void configure(final ResourceInfo info, final FeatureContext context) {
	final Method method = info.getResourceMethod();
	if (method.isAnnotationPresent(Deprecated.class)) {
	    final DeprecatedFilter filter = new DeprecatedFilter(info);
	    context.register(filter);
	}
    }
}
