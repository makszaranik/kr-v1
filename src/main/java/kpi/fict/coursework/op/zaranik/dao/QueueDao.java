package kpi.fict.coursework.op.zaranik.dao;

import java.util.Collection;
import kpi.fict.coursework.op.zaranik.model.Queue;

public interface QueueDao extends Dao<Queue> {
  void addItemByQueueId(int queueId, String item);
  Collection<String> getItemsByQueueId(int queueId);
  void removeItemFromQueue(int queueId, String item);
}
