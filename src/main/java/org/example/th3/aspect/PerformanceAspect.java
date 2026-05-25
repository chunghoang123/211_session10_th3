package org.example.th3.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    @Around(
            "execution(* org.example.th3.service.InventoryService.*(..))"
    )
    public Object measure(
            ProceedingJoinPoint joinPoint
    ) throws Throwable {

        long start =
                System.currentTimeMillis();

        try {

            Object result =
                    joinPoint.proceed();

            long time =
                    System.currentTimeMillis()
                            - start;

            log.info(
                    "Hàm {} chạy mất {} ms",
                    joinPoint.getSignature().getName(),
                    time
            );

            if(time > 500) {

                log.warn(
                        "[Performance Alert] Hàm {} quá chậm ({} ms)",
                        joinPoint.getSignature().getName(),
                        time
                );
            }

            return result;

        } catch (Exception e) {

            log.warn(
                    "Exception tại {} : {}",
                    joinPoint.getSignature().getName(),
                    e.getMessage()
            );

            throw e;
        }
    }
}