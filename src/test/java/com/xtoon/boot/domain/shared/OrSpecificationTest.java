package com.xtoon.boot.domain.shared;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * OrSpecificationTest
 *
 * @author haoxin
 * @date 2021-04-04
 **/
class OrSpecificationTest {

    @Test
    public void testAndIsSatisifedBy() {
        AlwaysTrueSpec trueSpec = new AlwaysTrueSpec();
        AlwaysFalseSpec falseSpec = new AlwaysFalseSpec();

        OrSpecification<Object> orSpecification = new OrSpecification<Object>(trueSpec, trueSpec);
        assertThat(orSpecification.isSatisfiedBy(new Object())).isTrue();

        orSpecification = new OrSpecification<Object>(falseSpec, trueSpec);
        assertThat(orSpecification.isSatisfiedBy(new Object())).isTrue();

        orSpecification = new OrSpecification<Object>(trueSpec, falseSpec);
        assertThat(orSpecification.isSatisfiedBy(new Object())).isTrue();

        orSpecification = new OrSpecification<Object>(falseSpec, falseSpec);
        assertThat(orSpecification.isSatisfiedBy(new Object())).isFalse();

    }
}