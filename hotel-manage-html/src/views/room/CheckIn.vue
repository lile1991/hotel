<template>
  <div>
    <el-form :model="checkInVo" :rules="checkInRules" ref="checkInForm" label-width="100px">
      <el-form-item label="房间类型" prop="roomTypeId" required>
        <el-select v-model.number="checkInVo.roomTypeId" placeholder="请选择" v-on:change="loadRooms">
          <el-option v-for="roomType in roomTypes" :key="roomType.id" :value="roomType.id" :label="roomType.name"/>
        </el-select>
      </el-form-item>
      <el-form-item label="入住房间" prop="roomId" required>
        <el-select v-model.number="checkInVo.roomId" placeholder="请选择" v-on:change="changeRoom">
          <el-option v-for="room in rooms" :key="room.id" :value="room.id" :label="room.alias"/>
        </el-select>
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="应付房费">
            <el-input v-model.number="selectedRoom.charge" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="实付房费" prop="payedCharge" required>
            <el-input v-model.number="checkInVo.payedCharge"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="应付押金">
            <el-input v-model.number="selectedRoom.deposit" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="实付押金" prop="payedDeposit" required>
            <el-input v-model.number="checkInVo.payedDeposit"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="入住日期" required>
        <el-date-picker
          v-model="checkInTimeRanges"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>


      <el-row :gutter="20" v-for="(checkInCustomer, index) in checkInCustomerList">
        <el-col :span="6">
          <el-form-item label="客户姓名" required>
            <el-input v-model="checkInCustomer.name" :rules="[{ required: true, message: '请填写客户姓名'}]"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="身份证号" required>
            <el-input v-model="checkInCustomer.idCard" :rules="[{ required: true, message: '请填写客户身份证号'}]"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="手机号">
            <el-input v-model="checkInCustomer.mobile"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-button type="primary" v-on:click="addCheckInCustomer"
                     v-if="index === checkInCustomerList.length - 1">添加入住人员
          </el-button>
          <el-button type="danger" v-on:click="removeCheckInCustomer(index)" v-else>删除</el-button>
        </el-col>
      </el-row>


      <el-form-item>
        <el-button type="primary" @click="submitForm('checkInForm')">登记</el-button>
        <el-button @click="resetForm('checkInForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  import RoomApi from '@/api/room'
  import RoomTypeApi from '@/api/roomType'
  import CheckRecordApi from '@/api/checkRecord'

  export default {
    data() {
      return {
        selectedRoom: {
          deposit: null,
          charge: null
        },
        roomTypes: [],
        rooms: [],
        checkInTimeRanges: [null, null],
        checkInVo: {
          roomTypeId: null,
          roomId: null,
          payedCharge: null,
          payedDeposit: null
        },
        checkInCustomerList: [{}],
        checkInRules: {
          roomTypeId: [
            {required: true, message: '请选择房间类型', trigger: 'blur'}
          ],
          roomId: [
            {required: true, message: '请选择入住房间', trigger: 'blur'}
          ],
          payedCharge: [
            {required: true, message: '请输入实付房费', trigger: 'blur'},
            {type: "number", message: '实付房费必须为数字', trigger: 'blur'}
          ],
          payedDeposit: [
            {required: true, message: '请输入实付押金', trigger: 'blur'},
            {type: "number", message: '实付押金必须为数字', trigger: 'blur'}
          ],
          checkInTimeRanges: [
            {type: 'array', required: true, message: '请选择入住日期', trigger: 'change'}
          ]
        }
      };
    },
    created() {
      this.loadRoomTypes();
    },
    methods: {
      submitForm(formName) {
        let checkInVo = this.checkInVo;
        checkInVo.checkInTime = this.checkInTimeRanges[0];
        checkInVo.checkOutTime = this.checkInTimeRanges[1];
        checkInVo.checkInCustomerList = this.checkInCustomerList;
        console.log("入住信息: " + JSON.stringify(this.checkInVo));
        this.$refs[formName].validate((valid) => {
          console.log(valid);
          if (valid) {
            CheckRecordApi.checkIn(checkInVo).then(response => {
              this.$message({
                type: 'success',
                message: response
              }.msg);
              this.$router.push({ path: '/room/manage'})
            });
          } else {
            this.$message.error("信息录入有误");
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      loadRooms() {
        if (this.checkInVo.roomTypeId) {
          RoomApi.findAll({roomType: {id: this.checkInVo.roomTypeId}}).then(response => {
            this.rooms = response.data;
          });
        }
      },
      loadRoomTypes() {
        RoomTypeApi.findAll().then(response => {
          this.roomTypes = response.data;
        });
      },
      changeRoom() {
        let selectedRoomId = this.checkInVo.roomId;
        for (let room of this.rooms) {
          if (room.id === selectedRoomId) {
            this.selectedRoom = room;
            break
          }
        }
      },
      addCheckInCustomer() {
        this.checkInCustomerList.push({});
      },
      removeCheckInCustomer(index) {
        this.checkInCustomerList.splice(index, 1);
      }
    }
  }
</script>
