package com.pkl.boot.common.Listener;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.pkl.boot.entity.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class DemoDataListener extends AnalysisEventListener<Dept> {
    private static final Logger logger = LoggerFactory.getLogger(DemoDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    private ExcelWriter excelWriter;
    private WriteSheet writeSheet;
    List<Dept> list = new ArrayList<Dept>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    public DemoDataListener(ExcelWriter excelWriter, WriteSheet writeSheet) {
        this.excelWriter = excelWriter;
        this.writeSheet = writeSheet;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(Dept data, AnalysisContext context) {
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(data);
        if(list.size()==5000){
            excelWriter.write(list, writeSheet);
            list.clear();
        }
    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if(!CollectionUtils.isEmpty(list)){
            excelWriter.write(list, writeSheet);
        }
        excelWriter.finish();
        logger.info("我已经被关闭");
    }
    /**
     * 加上存储数据库
     */
    private void saveData() {

    }
}
