package webhook.teamcity.payload.template;

import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SProject;
import lombok.Getter;
import webhook.teamcity.BuildStateEnum;
import webhook.teamcity.WebHookContentResolutionException;

@Getter
public class TemplateNotFoundException extends WebHookContentResolutionException {

	private static final long serialVersionUID = 3033989231876968261L;
	private final BuildStateEnum buildState;
	private final String projectId;
	private final String buildTypeId;
	private final String nonBranchOrBranch;
	private final String templateId;

	public TemplateNotFoundException(BuildStateEnum buildState, SBuildType buildType, String templateId, String nonBranchOrBranch) {
		super("Template '" + templateId + "' was not found for build state '" + buildState.getShortName() + "'", TEMPLATE_NOT_FOUND_ERROR_CODE);
		this.buildState = buildState;
		this.projectId = buildType.getProjectId();
		this.buildTypeId = buildType.getInternalId();
		this.templateId = templateId;
		this.nonBranchOrBranch = nonBranchOrBranch;
	}
	
	public TemplateNotFoundException(BuildStateEnum buildState, SProject project, String templateId, String nonBranchOrBranch) {
		super("Template '" + templateId + "' was not found for build state '" + buildState.getShortName() + "'", TEMPLATE_NOT_FOUND_ERROR_CODE);
		this.buildState = buildState;
		this.projectId = project.getProjectId();
		this.buildTypeId = "";
		this.templateId = templateId;
		this.nonBranchOrBranch = nonBranchOrBranch;
	}

}
