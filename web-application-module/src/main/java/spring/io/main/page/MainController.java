package spring.io.main.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    
    @GetMapping("/mainPage")
    public String getMainPage(Model model) {
        return "MainPage";
    }

    @RequestMapping("/addFigures")
    public String getAddFiguresPage(Model model) {
        return "AddFiguresPage";
    }

    @RequestMapping("/dragFigure")
    public String getDragFigurePage(Model model) {
        return "DragFigurePage";
    }

    @RequestMapping("/removeFigure")
    public String getRemoveFigurePage(Model model) {
        return "RemoveFigurePage";
    }

    @RequestMapping("/compute")
    public String getComputePage(Model model) {
        return "ComputePage";
    }

    @RequestMapping("/checkIfCross")
    public String getCheckIfCrossPage(Model model) {
        return "CheckIfCrossPage";
    }
}
