package au.com.qantas.freight.intlcust;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AgentRepository extends JpaRepository<Agent, String> {

    Agent findByIataCode(String agentCode);

}
