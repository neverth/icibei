package fun.neverth.icibei.authentication.provider;

import fun.neverth.icibei.common.core.vo.Result;
import fun.neverth.icibei.organization.entity.po.IResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * @author neverth.li
 * @date 2020/9/29 14:25
 */
@FeignClient(name = "icibei-organization")
public interface ResourceProvider {

    @GetMapping(value = "/resource/all")
    Result<Set<IResource>> resources();

    @GetMapping(value = "/resource/user/{username}")
    Result<Set<IResource>> resources(@PathVariable("username") String username);
}
