package sin.cloud.rabbitmqproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sin.cloud.rabbitmqproducer.pojo.User;
import sin.cloud.rabbitmqproducer.service.IUserService;

import java.util.List;

/**
 * User控制器
 * @author: crj
 * @date: 2018年8月20日 下午4:19:24
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    /**
     * 查询用户
     * @author: crj
     * @param model
     * @return
     * @date:2018年8月21日 上午11:27:11
     */
    @GetMapping
    public ModelAndView list(Model model) {
        // 查询所有的用户
        List<User> userList = userService.listUsers();
        model.addAttribute("userList",userList);
        model.addAttribute("title", "用户管理");
        return new ModelAndView("users/list","userModel",model);
    }
    /**
     * 根据用户id查看用户
     * @author: crj
     * @param id
     * @param model
     * @return
     * @date:2018年8月21日 上午11:30:43
     */
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id,Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view","userModel",model);
    }

    /**
     * 获取创建表单页面
     * @author: crj
     * @param model
     * @return
     * @date:2018年8月21日 上午11:34:57
     */
    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form","userModel",model);
    }
    /**
     * 保存或者更新用户，并返回用户列表
     * @author: crj
     * @param user
     * @return
     * @date:2018年8月21日 上午11:43:07
     */
    @PostMapping
    public ModelAndView save(User user) {
        userService.save(user);
        return new ModelAndView("redirect:/users");//重定向到list页面
    }
    /**
     * s删除用户，并返回用户列表
     * @author: crj
     * @param id
     * @return
     * @date:2018年8月22日 上午9:27:49
     */
    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users");//重定向到list页面
    }
    /**
     * 修改用户跳转页面
     * @author: crj
     * @param model
     * @return
     * @date:2018年8月22日 上午9:27:31
     */
    @GetMapping("/modify/{id}")
    public ModelAndView modifyUser(@PathVariable("id") Long id,Model model) {
        model.addAttribute("user",userService.getUserById(id));
        model.addAttribute("title", "修改用户");
        return new ModelAndView("users/modify","userModel",model);
    }

    @PostMapping("/update")
    public ModelAndView updateUser(User user){
        userService.update(user);
        return new ModelAndView("redirect:/users");//重定向到list页面
    }

}
