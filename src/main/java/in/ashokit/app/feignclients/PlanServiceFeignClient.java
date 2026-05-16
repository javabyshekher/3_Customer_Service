package in.ashokit.app.feignclients;

import in.ashokit.app.model.PlanData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PLANSERVICE")
public interface PlanServiceFeignClient {
    @GetMapping(value = "/api/v1/plans/{id}")
    PlanData fetchPlanData(@PathVariable String id);
}
