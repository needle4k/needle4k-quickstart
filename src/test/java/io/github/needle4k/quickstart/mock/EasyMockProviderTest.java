package io.github.needle4k.quickstart.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static io.github.needle4k.configuration.ConfigurationLoaderKt.MOCK_PROVIDER_KEY;

import java.util.Map;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import io.github.needle4k.annotation.ObjectUnderTest;
import io.github.needle4k.junit5.NeedleExtension;
import io.github.needle4k.mock.EasyMockProvider;
import io.github.needle4k.mock.MockProvider;

import jakarta.inject.Inject;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class EasyMockProviderTest
{
  @RegisterExtension
  private static final NeedleExtension needleExtension = new NeedleExtension()
  {
    protected void configure()
    {
      final Map<String, String> properties = getNeedleConfiguration().getConfigurationProperties();
      properties.put(MOCK_PROVIDER_KEY, EasyMockProvider.class.getName());
    }
  };

  @ObjectUnderTest(implementation = MyComponentBean.class)
  private MyComponent component;

  @Inject
  private MockProvider mockProvider;

  @Test
  public void testStrictMock()
  {
    final MyEjbComponent myEjbComponentMock = needleExtension.getInjectedObject(MyEjbComponent.class);
    assertNotNull(myEjbComponentMock);

    EasyMock.resetToStrict(myEjbComponentMock);
    EasyMock.expect(myEjbComponentMock.doSomething()).andReturn("Hallo, Welt!").anyTimes();

    easymock().replayAll();
    final String result = component.testMock();

    assertEquals("Hallo, Welt!", result);

    easymock().verifyAll();
  }

  private EasyMockProvider easymock()
  {
    return (EasyMockProvider) mockProvider;
  }
}
