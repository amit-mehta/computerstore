package au.com.qantas.freight.intlcust;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSummaryRepository extends JpaRepository<CustomerSummary, String> {

    CustomerSummary findByCustomerCode(String agentCode);
}
