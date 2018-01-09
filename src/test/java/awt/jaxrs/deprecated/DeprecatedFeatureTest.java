package awt.jaxrs.deprecated;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;

import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeprecatedFeatureTest {

    @Mock
    private ResourceInfo info;

    @Mock
    private FeatureContext context;

    @Mock
    private Method method;

    @Test
    public void testNoDeprecatedAnnotation() {
	when(this.info.getResourceMethod()).thenReturn(this.method);
	when(this.method.isAnnotationPresent(Deprecated.class)).thenReturn(false);
	final DeprecatedFeature feature = new DeprecatedFeature();
	feature.configure(this.info, this.context);
	verify(this.context, never()).register(any(DeprecatedFilter.class));
    }

    @Test
    public void testHasDeprecatedAnnotation() {
	when(this.info.getResourceMethod()).thenReturn(this.method);
	when(this.method.isAnnotationPresent(Deprecated.class)).thenReturn(true);
	final DeprecatedFeature feature = new DeprecatedFeature();
	feature.configure(this.info, this.context);
	verify(this.context, times(1)).register(any(DeprecatedFilter.class));
    }
}
