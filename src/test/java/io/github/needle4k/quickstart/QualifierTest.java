package io.github.needle4k.quickstart;

import static io.github.needle4k.injection.InjectionProviders.providerForQualifiedInstance;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.github.needle4k.annotation.ObjectUnderTest;
import io.github.needle4k.junit5.NeedleExtension;
import io.github.needle4k.quickstart.annotations.CurrentUser;
import io.github.needle4k.quickstart.user.User;
import io.github.needle4k.quickstart.user.dao.PersonService;
import io.github.needle4k.reflection.ReflectionUtil;
import jakarta.inject.Inject;

/**
 * Example for custom injection provider for "qualified" CDI component injection
 */
@SuppressWarnings("CdiInjectionPointsInspection")
public class QualifierTest
{
  private static final User USER = new User("heinz");

  @RegisterExtension
  public static final NeedleExtension needleExtension = new NeedleExtension(
      providerForQualifiedInstance(CurrentUser.class, USER));

  @Inject
  private User someUser;

  @ObjectUnderTest
  private PersonService objectUnderTest;

  @Test
  public void testQualifier()
  {
    final User currentUser = (User) ReflectionUtil.getFieldValue(objectUnderTest, "currentUser");

    assertThat(someUser).isNotSameAs(currentUser);
    assertThat(currentUser).isSameAs(USER);
  }
}
