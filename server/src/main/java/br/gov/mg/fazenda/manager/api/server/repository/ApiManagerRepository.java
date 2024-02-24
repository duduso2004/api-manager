package br.gov.mg.fazenda.manager.api.server.repository;

import br.gov.mg.fazenda.manager.api.server.entity.ApiManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiManagerRepository extends JpaRepository<ApiManager, Long> {

}
