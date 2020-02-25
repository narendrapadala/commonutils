package corp.asbp.platform.commonutils.cu.filter.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import corp.asbp.platform.commonutils.cu.util.CommonSpringConditionUtil;

public class CommonFilterCondition implements Condition {
	private static Logger LOGGER = LoggerFactory.getLogger(CommonFilterCondition.class);
	
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		LOGGER.info("creating common filter condition...");
		return CommonSpringConditionUtil.isConditionSatisfied(context, "common.filter.disabled", true) ? false : true;
	}
}
