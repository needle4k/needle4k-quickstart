package io.github.needle4k.quickstart.mock;

import jakarta.ejb.Local;

@Local
public interface MyEjbComponent {
  String doSomething();
}
