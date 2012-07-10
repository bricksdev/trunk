/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.form.implement;

import cn.com.annotations.Group;
import cn.com.annotations.parser.AnnotationParser;
import cn.com.tags.form.ContainHTMLElementTag;
import cn.com.tags.form.ContextComponent;
import cn.com.tags.form.component.ActionComponent;
import cn.com.tags.form.component.ConditionComponent;
import cn.com.tags.form.component.TabIndex;
import cn.com.tags.form.component.TableComponent;
import cn.com.tags.form.containstag.DivElementTag;
import cn.com.template.form.FormTemplate;
import cn.com.wapps.permission.Permission;

/**
 * <pre>
 *     <from>
 *          <div id="conditions">
 *              <div id="condition">
 *                  <label>ID</label>
 *                  <div id=required class= style=color:red>*</div>
 *                  <input type="text" name="id" />
 *              </div>
 *              <div id=fileDiv>
 *                  <input type=file id=fileName /> <input type=button name=import1 value=导入 />
 *              </div>
 *
 *          </div>
 *          <div id="grid">
 *              <table >
 *                  <th>
 *                      <td>列名</td>
 *                  </th>
 *                  <tr>
 *                      <td>1234</td>
 *                  </tr>
 *              </table>
 *          </div>
 *          <input type=button name=save value=提交 />
 *          <input type=button name=save value=重置 />
 *     </form>
 * </pre>
 *
 * @author kete
 */
public class DefaultFormTemplate extends FormTemplate {

    @Override
    protected void parserComponents(AnnotationParser parser, ContainHTMLElementTag parentElement, Group group, Permission permission) {

        if (group != null) {
            DivElementTag contentDiv = new DivElementTag(group.name(), "form_content");

            ContextComponent context = new ContextComponent(contentDiv, parser, new TabIndex());
            //条件标签
            context.addComponent(new ConditionComponent(permission));
            // 表格标签
            context.addComponent(new TableComponent());
            // 事件标注
            context.addComponent(new ActionComponent(permission));

            context.processComponents();
            parentElement.addChildrenTag(contentDiv);
        }
    }
}
