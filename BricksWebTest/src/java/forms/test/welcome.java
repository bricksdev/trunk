/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.test;

import java.util.Map;

/**
 *
 * @author kete
 */
public class welcome {

    public String login(Map request, Map session) {
//        IAuthenticationService authService = IService.getService(IAuthenticationService.class);
//        String username = FormUtil.getString("name", request);
//        String password = FormUtil.getString("password", request);
//        String url = FormUtil.getString("returnUrl", request);
//        String authCode = FormUtil.getString("authCode", request);
//        authCode = "0000";
//        session.put("authCode", authCode);
//        // <editor-fold defaultstate="collapsed" desc="验证验证码输入是否正确">
//        if (authCode == null || authCode.trim().equals("")) {
////            request.put("error", "验证码不能为空！");
//            request.put("error", byd.wms.common.util.LanguageNational.getValue("E6000012"));
//            return null;
//        }
//        String authCodeSession = FormUtil.getString("authCode", session);
//        session.remove("authCode");
//        ActionContext.getRequest().getSession().removeAttribute("authCode");
//        if (authCodeSession == null || !authCodeSession.trim().equals(authCode.trim())) {
////            request.put("error", "验证码验证失败！");
//            request.put("error", byd.wms.common.util.LanguageNational.getValue("M6000013"));
//            return null;
//        }
//        //</editor-fold>
//        if (username == null || username.trim().equals("")) {
////            request.put("error", "登录名不能为空！");
//            request.put("error", byd.wms.common.util.LanguageNational.getValue("E6000013"));
//            return null;
//        }
//        if (password == null || password.trim().equals("")) {
////            request.put("error", "密码不能为空！");
//            request.put("error", byd.wms.common.util.LanguageNational.getValue("E6000014"));
//            return null;
//        }
//        try {
//            authService.login(username, password);
//        } catch (ServiceException ex) {
//            request.put("error", "" + ex.getDescription());
//            return null;
//        } catch (Throwable ex) {
//            request.put("error", "" + ex.getMessage());
//            return null;
//        }
//
//        url = FormUtil.getParam("returnUrl", request);
//
//        if (url == null || url.trim().equals("")) {
//            url = "home/index.jsp";
//        }
//        //设置语言
//        new LanguageNational((String) ActionContext.getRequest().getSession().getAttribute("language"));
//
//        return FormStatics.ACTIONRETURN_REDIRECT + ActionContext.getResponse().encodeRedirectURL(url);
        return null;
    }
}
