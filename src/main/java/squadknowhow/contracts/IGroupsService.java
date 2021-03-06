package squadknowhow.contracts;

import org.springframework.web.multipart.MultipartFile;
import squadknowhow.dbmodels.Group;
import squadknowhow.dbmodels.GroupShort;
import squadknowhow.response.models.ResponseCheckGroupName;
import squadknowhow.response.models.ResponseGroupId;
import squadknowhow.response.models.ResponsePagination;
import squadknowhow.response.models.ResponseUpload;

import java.io.IOException;
import java.util.List;

public interface IGroupsService {
  ResponseGroupId createGroup(Group group, int creatorId);

  ResponseUpload uploadImage(MultipartFile multipart, int id) throws IOException;

  Group getGroupById(int id);

  ResponsePagination getGroupPages(String name);

  ResponseCheckGroupName checkGroupName(String name);

  List<GroupShort> getGroups(int page, String name);

  boolean addGroupMember(int groupId, int userId);
}
