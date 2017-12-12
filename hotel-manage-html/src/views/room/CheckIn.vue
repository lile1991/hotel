<template>
  <div>
    <el-form :model="checkRecord" :rules="rules" ref="checkRecordForm" label-width="100px">
      <el-form-item label="房间类型" prop="roomTypeId" required>
        <el-select v-model="roomTypeId" placeholder="请选择" v-on:change="loadRooms">
          <el-option v-for="roomType in roomTypes" :key="roomType.id" :value="roomType.id" :label="roomType.name"/>
        </el-select>
      </el-form-item>
      <el-form-item label="入住房间" prop="roomId" required>
        <el-select v-model="checkRecord.room.id" placeholder="请选择" v-on:change="changeRoom">
          <el-option v-for="room in rooms" :key="room.id" :value="room.id" :label="room.alias"/>
        </el-select>
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="应付房费" prop="charge">
            <el-input v-model="selectedRoom.charge" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="实付房费" prop="payedCharge" required>
            <el-input v-model="checkRecord.payedCharge"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="应付押金" prop="customerIdCard">
            <el-input v-model="selectedRoom.deposit" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="实付押金" prop="payedDeposit" required>
            <el-input v-model="checkRecord.payedDeposit"></el-input>
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


      <el-row :gutter="20" v-for="(checkInCustomer, index) in checkRecord.checkInCustomerList">
        <el-col :span="6">
          <el-form-item label="客户姓名" prop="checkInCustomerName" required>
            <el-input v-model="checkInCustomer.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="手机号" prop="checkInCustomerMobile" required>
            <el-input v-model="checkInCustomer.mobile"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="身份证号" prop="checkInCustomerIdCard" required>
            <el-input v-model="checkInCustomer.idCard"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-button type="primary" v-on:click="addCheckInCustomer"
                     v-if="index === checkRecord.checkInCustomerList.length - 1">添加入住人员
          </el-button>
          <el-button type="danger" v-on:click="removeCheckInCustomer(index)" v-else>删除</el-button>
        </el-col>
      </el-row>


      <el-form-item>
        <el-button type="primary" @click="submitForm('checkRecordForm')">登记</el-button>
        <el-button @click="resetForm('checkRecordForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  import RoomApi from '@/api/room'
  import RoomTypeApi from '@/api/roomType'

  export default {
    data() {
      return {
        roomTypeId: null,
        selectedRoom: {},
        roomTypes: [],
        rooms: [],
        checkInTimeRanges: [null, null],
        checkRecord: {
          room: {},
          checkInCustomerList: [{}],
        },
        rules: {
          roomTypeId: [
            {required: true, message: '请选择房间类型', trigger: 'blur'}
          ],
          roomId: [
            {required: true, message: '请选择入住房间', trigger: 'blur'}
          ],
          payedCharge: [
            {required: true, message: '请输入实付房费', trigger: 'blur'}
          ],
          payedDeposit: [
            {required: true, message: '请输入实付押金', trigger: 'blur'}
          ],
          checkInTimeRanges: [
            {type: 'date', required: true, message: '请选择入住日期', trigger: 'blur'}
          ],
          checkInCustomerName: [
            {required: true, message: '请输入顾客姓名', trigger: 'blur'}
          ],
          checkInCustomerMobile: [
            {required: true, message: '请输入顾客手机号', trigger: 'blur'}
          ],
          checkInCustomerIdCard: [
            {required: true, message: '请输入顾客身份证号', trigger: 'blur'}
          ]
        }
      };
    },
    created() {
      this.loadRoomTypes();
    },
    methods: {
      submitForm(formName) {
        console.log("入住信息: " + JSON.stringify(this.checkRecord));
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      loadRooms() {
        if(this.roomTypeId) {
          RoomApi.findAll({roomType: {id: this.roomTypeId}}).then(response => {
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
        let selectedRoomId = this.checkRecord.room.id;
        for (let room of this.rooms) {
          if (room.id === selectedRoomId) {
            this.selectedRoom = room;
            break
          }
        }
      },
      addCheckInCustomer() {
        this.checkRecord.checkInCustomerList.push({});
      },
      removeCheckInCustomer(index) {
        this.checkRecord.checkInCustomerList.splice(index, 1);
      }
    }
  }
</script>
