
package org.example.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {

   // private val logger = LoggerFactory.getLogger(this::class.java)

    @Around("execution(* org.example.service.*.*(..))")
    fun logServiceMethods(joinPoint: ProceedingJoinPoint): Any {
        val methodName = joinPoint.signature.name
        val start = System.currentTimeMillis()
        //logger.info("[SERVICE] Starting operation: {}", methodName)
        println("[SERVICE] Starting operation: $methodName")
        return try {
            val result = joinPoint.proceed()
            val duration = System.currentTimeMillis() - start
            //"[SERVICE] Completed operation: {} in {} ms", methodName, duration)
            println("[SERVICE] Completed operation: {$methodName} in {$duration} ms")
            result
        } catch (ex: Throwable) {
            val duration = System.currentTimeMillis() - start
            //logger.error("[SERVICE] Failed operation: {} after {} ms with error: {}", methodName, duration, ex.message, ex)
            println("logger.error([SERVICE] Failed operation: {$methodName} after {$duration} ms with error: {${ex.message}}")
            throw ex
        }
    }
}
