package fun.neverth.icibei.sysadmin.organization.controller;

import fun.neverth.icibei.sysadmin.organization.entity.form.GatewayRouteForm;
import fun.neverth.icibei.sysadmin.organization.entity.form.GatewayRouteQueryForm;
import fun.neverth.icibei.sysadmin.organization.entity.param.GatewayRouteQueryParam;
import fun.neverth.icibei.sysadmin.organization.entity.po.GatewayRoute;
import fun.neverth.icibei.sysadmin.organization.entity.vo.GatewayRouteVO;
import fun.neverth.icibei.common.core.vo.Result;
import fun.neverth.icibei.sysadmin.organization.service.GatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 23:25
 */
@RestController
@RequestMapping("/gateway/routes")
@Slf4j
public class GatewayRouteController {

    @Resource
    private GatewayRouteService gatewayRouteService;

    @PostMapping("")
    public Result add(@Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        GatewayRoute gatewayRout = gatewayRoutForm.toPO(GatewayRoute.class);
        return Result.success(gatewayRouteService.add(gatewayRout));
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(gatewayRouteService.delete(id));
    }

    @PutMapping(value = "/{id}")
    public Result update(
            @PathVariable String id,
            @Valid @RequestBody GatewayRouteForm gatewayRoutForm
    ) {
        GatewayRoute gatewayRout = gatewayRoutForm.toPO(GatewayRoute.class);
        gatewayRout.setId(id);
        return Result.success(gatewayRouteService.update(gatewayRout));
    }

    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        log.info("get with id:{}", id);
        return Result.success(new GatewayRouteVO(gatewayRouteService.get(id)));
    }

    @GetMapping
    public Result getByUri(@RequestParam String uri) {
        return Result.success(
                gatewayRouteService.query(
                        new GatewayRouteQueryParam(uri)
                ).stream().findFirst()
        );
    }

    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody GatewayRouteQueryForm gatewayRouteQueryForm) {
        return Result.success(
                gatewayRouteService.query(
                        gatewayRouteQueryForm.toParam(GatewayRouteQueryParam.class)
                )
        );
    }

    @PostMapping(value = "/overload")
    public Result overload() {
        return Result.success(gatewayRouteService.overLoadToCache());
    }

}