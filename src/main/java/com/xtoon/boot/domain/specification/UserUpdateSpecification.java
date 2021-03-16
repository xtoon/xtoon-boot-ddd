package com.xtoon.boot.domain.specification;

import com.xtoon.boot.domain.model.system.Tenant;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.repository.TenantRepository;
import com.xtoon.boot.domain.shared.AbstractSpecification;

/**
 * 用户修改Specification
 *
 * @author haoxin
 * @date 2021-02-27
 **/
public class UserUpdateSpecification extends AbstractSpecification<User> {

    private TenantRepository tenantRepository;

    public UserUpdateSpecification(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public boolean isSatisfiedBy(User user) {
        Tenant tenant = tenantRepository.find(user.getTenant().getTenantId());
        if(tenant.getCreator().sameIdentityAs(user)) {
            throw new RuntimeException("租户创建者无法修改");
        }
        return false;
    }
}
