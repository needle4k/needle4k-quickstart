package org.needle4k.quickstart.injection;

import java.util.Map;
import java.util.Queue;

import org.needle4k.quickstart.annotations.CustomInjectionAnnotation1;
import org.needle4k.quickstart.annotations.CustomInjectionAnnotation2;

public class CustomInjectionTestComponent
{
  @CustomInjectionAnnotation1
  private Queue<String> queue1;

  @CustomInjectionAnnotation2
  private Queue<String> queue2;

  @CustomInjectionAnnotation1
  private Map<String, String> map;

  public Queue<String> getQueue1() {
    return queue1;
  }

  public Queue<String> getQueue2() {
    return queue2;
  }

  public Map<String, String> getMap() {
    return map;
  }
}
