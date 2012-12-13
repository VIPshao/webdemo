//// CODECHECKING 代码走查时间:2012年11月28日 何雨
//package com.boda.components.servicelog;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.helpers.MarkerIgnoringBase;
//import org.slf4j.helpers.MessageFormatter;
//import org.springframework.stereotype.Component;
//
//import com.boda.components.servicelog.service.LogService;
//import com.boda.domain.system.Authority;
//import com.boda.domain.system.HandleLog;
//import com.boda.domain.system.Menu;
//import com.boda.domain.system.Organization;
//import com.boda.domain.system.Post;
//import com.boda.domain.system.User;
//import com.boda.ios.log.context.LoggerContext;
//import com.boda.ios.log.custominterface.LoggerInterface;
//import com.boda.ios.log.exception.LoggerException;
//import com.boda.ios.log.model.MethodInfoDocument;
//import com.core.system.AuthorityUtil;
//import com.core.util.DateUtil;
//import com.core.util.SysRuntimeUtil;
//
///**
// * 日志
// * 
// * @author liujun
// */
//@Component(value = "businessLogger")
//public class ServiceLogger extends MarkerIgnoringBase {
//	private static final long serialVersionUID = -4256501532148702677L;
//
//	private Logger logger = LoggerFactory.getLogger(ServiceLogger.class);
//
//	private LogService logService;
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 * @param arg2
//	 */
//	@Override
//	public void debug(String arg0, Object arg1, Object arg2) {
//		writeLog(arg0, arg1, arg2);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void debug(String arg0, Object arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void debug(String arg0, Object[] arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void debug(String arg0, Throwable arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 */
//	@Override
//	public void debug(String arg0) {
//		writeLog(arg0);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 * @param arg2
//	 */
//	@Override
//	public void error(String arg0, Object arg1, Object arg2) {
//		writeLog(arg0, arg1, arg2);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void error(String arg0, Object arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void error(String arg0, Object[] arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void error(String arg0, Throwable arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 */
//	@Override
//	public void error(String arg0) {
//		writeLog(arg0);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 * @param arg2
//	 */
//	@Override
//	public void info(String arg0, Object arg1, Object arg2) {
//		writeLog(arg0, arg1, arg2);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void info(String arg0, Object arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void info(String arg0, Object[] arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void info(String arg0, Throwable arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 */
//	@Override
//	public void info(String arg0) {
//		writeLog(arg0);
//	}
//
//	/**
//	 * @return
//	 */
//	@Override
//	public boolean isDebugEnabled() {
//		return true;
//	}
//
//	/**
//	 * @return
//	 */
//	@Override
//	public boolean isErrorEnabled() {
//		return true;
//	}
//
//	/**
//	 * @return
//	 */
//	@Override
//	public boolean isInfoEnabled() {
//		return true;
//	}
//
//	/**
//	 * @return
//	 */
//	@Override
//	public boolean isTraceEnabled() {
//		return false;
//	}
//
//	/**
//	 * @return
//	 */
//	@Override
//	public boolean isWarnEnabled() {
//		return true;
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 * @param arg2
//	 */
//	@Override
//	public void trace(String arg0, Object arg1, Object arg2) {
//		writeLog(arg0, arg1, arg2);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void trace(String arg0, Object arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void trace(String arg0, Object[] arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void trace(String arg0, Throwable arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 */
//	@Override
//	public void trace(String arg0) {
//		writeLog(arg0);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 * @param arg2
//	 */
//	@Override
//	public void warn(String arg0, Object arg1, Object arg2) {
//		writeLog(arg0, arg1, arg2);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void warn(String arg0, Object arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void warn(String arg0, Object[] arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	@Override
//	public void warn(String arg0, Throwable arg1) {
//		writeLog(arg0, arg1);
//	}
//
//	/**
//	 * @param arg0
//	 */
//	@Override
//	public void warn(String arg0) {
//		writeLog(arg0);
//	}
//
//	/**
//	 * @return 返回 logService
//	 */
//	public LogService getLogService() {
//		return logService;
//	}
//
//	/**
//	 * @param 对logService进行赋值
//	 */
//	public void setLogService(LogService logService) {
//		this.logService = logService;
//	}
//
//	/**
//	 * 记录日志
//	 * 
//	 * @author liujun
//	 * @param msg
//	 *            要写的信息
//	 * @param objs
//	 *            [参数说明]
//	 * 
//	 * @return void [返回类型说明]
//	 * @exception throws [异常类型] [异常说明]
//	 * @see [类、类#方法、类#成员]
//	 */
//	private void writeLog(String messagePattern, Object... objs) {
//		try {
//			// 获取调用堆栈
//			StackTraceElement callStack = getCallMethodStack();
//			// 格式化消息
//			String message = MessageFormatter.arrayFormat(messagePattern, objs)
//					.getMessage();
//
//			// 生成日志对象
//			HandleLog handleLog = new HandleLog();
//			handleLog.setContext(message);
//
//			// 获取当前日志调用堆栈，及其说明
//			String className = callStack.getClassName();
//			String methodName = callStack.getMethodName();
//			String callCallAndMethodName = className + "." + methodName;
//
//			handleLog.setMethodPath(callCallAndMethodName);
//			handleLog.setMethodName(methodName);
//			handleLog.setClassPath(className);
//
//			MethodInfoDocument document = LoggerContext.getContext()
//					.getMethodInfoByPath(callCallAndMethodName);
//
//			if (null != document) {
//				handleLog.setMethodInfo(callCallAndMethodName);
//			}
//
//			setFunctionInfoByMethodAndClazz(className, methodName, handleLog);
//
//			// 获取当前使用人员
//			User user = SysRuntimeUtil.getLoginUser();
//			if (null != user) {
//				handleLog.setEmpId(user.getId());
//				handleLog.setEmpName(user.getEmployee().getName());
//				handleLog.setEmpCode(user.getLoginname());
//
//				Post p = user.getCurPost();
//				if (null != p) {
//					handleLog.setPostId(p.getId());
//					handleLog.setPostCode(p.getCode());
//					handleLog.setPostName(p.getName());
//
//					Organization org = p.getOrg();
//					if (null != org) {
//						handleLog.setOrgCode(org.getCode());
//						handleLog.setOrgId(org.getId());
//						handleLog.setOrgName(org.getName());
//					}
//				}
//
//			}
//			handleLog.setDate(DateUtil.getDate());
//
//			logService.insertHandleLog(handleLog);
//		} catch (Throwable e) {
//			logger.error(e.toString(), e);
//			throw new LoggerException(e);
//		}
//	}
//
//	/**
//	 * 根据方法名和类路径设置日志的功能信息,设置成功返回true,否则返回false
//	 * 
//	 * @author liujun
//	 * 
//	 * @param classPath
//	 *            类路径
//	 * @param methodName
//	 *            方法名称
//	 * @param log
//	 *            日志对象
//	 * @return 成功返回true,否则返回false
//	 * */
//	private void setFunctionInfoByMethodAndClazz(String classPath,
//			String methodName, HandleLog log) {
//		LoggerInterface logger = LoggerContext.getContext().getLoggerInterface(
//				classPath, methodName);
//		if (null == logger) {
//			setFunctionInfoByFunctionId(log);
//		} else {
//			log.setAuthorityName(logger.autEnum().name());
//			log.setMenuName(logger.menuName());
//		}
//
//	}
//
//	/**
//	 * 根据functionId设置日志的功能信息,设置成功返回true,否则返回false
//	 * 
//	 * @author liujun
//	 * @param log
//	 *            日志对象
//	 * @return 成功返回true,否则返回false
//	 * */
//	private boolean setFunctionInfoByFunctionId(HandleLog handleLog) {
//		// 获取当前业务日志功能
//		String functionId = AuthorityUtil.getFunctionId();
//		if (null != functionId) {
//			handleLog.setAutId(functionId);
//			Authority authority = AuthorityUtil.getInstance()
//					.getAuthoritiesMap().get(functionId);
//			if (null != authority) {
//				handleLog.setAuthorityName(authority.getName());
//				Menu menu = authority.getMenu();
//				if (null != menu) {
//					handleLog.setMenuId(String.valueOf(menu.getId()));
//					handleLog.setMenuName(menu.getName());
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	/**
//	 * PRO 刘军 注释错误.是得到调用方法的堆栈信息 得到调用方法
//	 * 
//	 * @author liujun
//	 * */
//	private StackTraceElement getCallMethodStack() {
//		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
//		if (stacks == null || stacks.length < 5) {
//			return null;
//		}
//		return stacks[4];
//	}
//}
