package com.agussuhardi.springdemofull.config.security.facade.auditor;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
//    var userAccount = UserInfo.getAccountPrincipal();
//    if (userAccount != null && userAccount.getId() != null)
//      return Optional.of(userAccount.getId().toString());
        return Optional.empty();
    }
}
