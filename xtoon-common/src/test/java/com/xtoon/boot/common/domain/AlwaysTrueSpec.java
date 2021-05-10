package com.xtoon.boot.common.domain;

import com.xtoon.boot.common.domain.AbstractSpecification;

public class AlwaysTrueSpec extends AbstractSpecification<Object> {
  public boolean isSatisfiedBy(Object o) {
    return true;
  }
}
