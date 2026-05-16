package in.ashokit.app.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "FRIENDSERVICE")
public interface FriendServiceFeignClient {
    @GetMapping(value = "/api/v1/friend-contact/{phoneNumber}")
    List<Object[]> fetchFriendsContacts(@PathVariable long phoneNumber);
}
