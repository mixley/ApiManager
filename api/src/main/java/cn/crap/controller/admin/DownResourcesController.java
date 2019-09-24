package cn.crap.controller.admin;

import cn.crap.framework.base.BaseController;
import cn.crap.model.FileSave;
import cn.crap.model.FileSaveCriteria;
import cn.crap.service.FileSaveService;
import cn.crap.service.tool.SettingCache;
import cn.crap.utils.FileUtil;
import cn.crap.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.List;

/**
 * @Author: 李志锐
 * @CreateTime: 2019-09-06 15:04
 * @Description:
 **/

@Controller
@RequestMapping("/downResources")
public class DownResourcesController extends BaseController {
    @Autowired
    private SettingCache settingCache;
    @Autowired
    private FileSaveService fileSaveService;

    @RequestMapping("/{dir}/{date}/{resFilePath:.*}")
    @ResponseBody
    public void downResources(HttpServletRequest request, HttpServletResponse response, @PathVariable String dir, @PathVariable String date, @PathVariable String resFilePath) throws Exception {
        String destDir = Tools.getCanWriteDestDir();
        String filePath = "resources/upload/" + dir + "/" + date + "/" + resFilePath;
        File file = new File(destDir + filePath);
        if (file == null || !file.exists()) {
//            continue;
            //从数据库读取
            try {
                FileSaveCriteria fileSaveCriteria = new FileSaveCriteria();
                fileSaveCriteria.createCriteria().andFilenameEqualTo(filePath);
                List<FileSave> fileSaves = fileSaveService.selectByExampleWithBLOBs(fileSaveCriteria);
                if (null != fileSaves && fileSaves.size() > 0) {
                    FileSave fileSave = fileSaves.get(0);
                    byte[] fileblob = fileSave.getFileblob();
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    response.addHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
                    if (file.canWrite()) {
                        file.createNewFile();
                        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                            fileOutputStream.write(fileblob);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    response.setContentLength(fileblob.length);
                    try (OutputStream os = response.getOutputStream()) {
                        os.write(fileblob);
                    }
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            response.addHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            byte[] data = FileUtil.getFileBytes(file);
            response.setContentLength(data.length);
            try (OutputStream os = response.getOutputStream()){
                os.write(data);
            }
        }
//        response.sendRedirect(settingCache.getDomain() + "/" + filePath);

    }
}
