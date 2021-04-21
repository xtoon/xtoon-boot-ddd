package com.xtoon.boot.domain.shared;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * NotSpecificationTest
 *
 * @author haoxin
 * @date 2021-04-04
 **/
class NotSpecificationTest {

    @Test
    public void testAndIsSatisifedBy() {
        AlwaysTrueSpec trueSpec = new AlwaysTrueSpec();
        AlwaysFalseSpec falseSpec = new AlwaysFalseSpec();

        NotSpecification<Object> notSpecification = new NotSpecification<Object>(trueSpec);
        assertThat(notSpecification.isSatisfiedBy(new Object())).isFalse();

        notSpecification = new NotSpecification<Object>(falseSpec);
        assertThat(notSpecification.isSatisfiedBy(new Object())).isTrue();

    }
}