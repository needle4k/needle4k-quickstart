package io.github.needle4k.quickstart.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import io.github.needle4k.annotation.ObjectUnderTest;
import io.github.needle4k.injection.InjectionProvider;
import io.github.needle4k.injection.InjectionTargetInformation;
import io.github.needle4k.junit5.NeedleExtension;

/**
 * Example shows how to test Spring components in isolation.
 * It also demonstrates how constructor injection may be used.
 */
public class FooServiceTest
{
  @RegisterExtension
  private static final NeedleExtension needleExtension = new NeedleExtension(new FormatterInjectionProvider(new FooFormatter()));

  @ObjectUnderTest
  private FooService fooService;

  @Test
  public void whenFooFormatterType_thenReturnFoo()
  {
    assertEquals("foo", fooService.doStuff());
  }

  private static class FormatterInjectionProvider implements InjectionProvider<Formatter>
  {
    private final Formatter instance;

    public FormatterInjectionProvider(final Formatter instance) {
      this.instance = instance;
    }

    @Override
    public Formatter getInjectedObject(final Class<?> injectionTargetType)
    {
      return instance;
    }

    @Override
    public Object getKey(final InjectionTargetInformation<?> injectionTargetInformation)
    {
      return injectionTargetInformation.getInjectedObjectType();
    }

    @Override
    public boolean verify(final InjectionTargetInformation<?> injectionTargetInformation)
    {
      return injectionTargetInformation.getInjectedObjectType().isAssignableFrom(instance.getClass());
    }
  }
}