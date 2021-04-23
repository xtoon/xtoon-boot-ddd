package com.xtoon.boot.sys.domain.shared;

import com.xtoon.boot.common.domain.AbstractSpecification;

public class AlwaysFalseSpec extends AbstractSpecification<Object> {
  public boolean isSatisfiedBy(Object o) {
    return false;
  }
}