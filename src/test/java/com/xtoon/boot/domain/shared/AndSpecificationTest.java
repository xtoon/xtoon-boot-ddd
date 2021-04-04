package com.xtoon.boot.domain.shared;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AndSpecificationTest
 *
 * @author haoxin
 * @date 2021-04-04
 **/
class AndSpecificationTest {

    @Test
    public void testAndIsSatisifedBy() {
        AlwaysTrueSpec trueSpec = new AlwaysTrueSpec();
        AlwaysFalseSpec falseSpec = new AlwaysFalseSpec();

        AndSpecification<Object> andSpecification = new AndSpecification<Object>(trueSpec, trueSpec);
        assertThat(andSpecification.isSatisfiedBy(new Object())).isTrue();

        andSpecification = new AndSpecification<Object>(falseSpec, trueSpec);
        assertThat(andSpecification.isSatisfiedBy(new Object())).isFalse();

        andSpecification = new AndSpecification<Object>(trueSpec, falseSpec);
        assertThat(andSpecification.isSatisfiedBy(new Object())).isFalse();

        andSpecification = new AndSpecification<Object>(falseSpec, falseSpec);
        assertThat(andSpecification.isSatisfiedBy(new Object())).isFalse();

    }

}