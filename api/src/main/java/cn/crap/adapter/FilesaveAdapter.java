package cn.crap.adapter;

import cn.crap.dto.FilesaveDto;
import cn.crap.model.FileSave;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Automatic generation by tools
 * model adapter: convert model to dto
 * Avoid exposing sensitive data and modifying data that is not allowed to be modified
 */
public class FilesaveAdapter {
    public static FilesaveDto getDto(FileSave model){
        if (model == null){
            return null;
        }

        FilesaveDto dto = new FilesaveDto();
        dto.setId(model.getId());
		dto.setFilename(model.getFilename());
		dto.setFileblob(model.getFileblob());
		dto.setCreatdate(model.getCreatdate());
		
        return dto;
    }

    public static FileSave getModel(FilesaveDto dto){
        if (dto == null){
            return null;
        }
        FileSave model = new FileSave();
        model.setId(dto.getId());
		model.setFilename(dto.getFilename());
		model.setFileblob(dto.getFileblob());
		model.setCreatdate(dto.getCreatdate());
		
        return model;
    }

    public static List<FilesaveDto> getDto(List<FileSave> models){
       if (CollectionUtils.isEmpty(models)){
                   return new ArrayList<>();
        }
        List<FilesaveDto> dtos = new ArrayList<>();
        for (FileSave model : models){
            dtos.add(getDto(model));
        }
        return dtos;
    }
}
