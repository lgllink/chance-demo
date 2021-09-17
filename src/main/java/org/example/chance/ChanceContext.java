package org.example.chance;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.chance.domain.ChanceRequest;
import org.example.chance.domain.TempChanceRecord;
import org.example.chance.utils.TempChance;

@Getter
@NoArgsConstructor
public class ChanceContext {

    private TempChanceEntity tempChanceEntity;

    private String uniqueId;

    private int addTimes = 1;

    public ChanceContext(ChanceRequest request){
        this.uniqueId = request.getUniqueId();
        this.addTimes = request.getAddTimes();
    }

    public void buildTempChanceEntity(TempChance tempChance) {
        this.tempChanceEntity = TempChanceEntity.build(tempChance);
    }

    public TempChance obtainTempChance() {
        return this.tempChanceEntity.obtainTempChance();
    }

    public void checkChance(TempChanceRecord tempChanceRecord) throws NullPointerException {
        this.getTempChanceEntity().checkChance(tempChanceRecord.getChance(), tempChanceRecord.getTodayChance(), this.addTimes);
    }

    public int getRealAddTimes() {
        return  this.obtainTempChance().getRealAddTimes();
    }


}
