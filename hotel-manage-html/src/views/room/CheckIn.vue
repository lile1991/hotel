<template>
  <div>
    <el-form :model="checkRecord" :rules="rules" ref="checkRecordForm" label-width="100px">
      <el-form-item label="房号" prop="roomNum" required>
        <el-select v-model="checkRecord.room.number" placeholder="请选择">
          <el-option v-for="room in rooms" :key="room.id" :value="room.id" :label="room.alias"/>
        </el-select>
      </el-form-item>
      <el-col :span="12">
        <el-form-item label="房费" prop="customerIdCard" required>
          <el-input v-model="checkRecord.room.name"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="实付房费" prop="customerIdCard" required>
          <el-input v-model="checkRecord.room.name"></el-input>
        </el-form-item>
      </el-col>

      <el-form-item label="押金" prop="customerIdCard" required>
        <el-input v-model="checkRecord.room.name"></el-input>
      </el-form-item>
      <el-form-item label="入住日期" required>
        <el-date-picker
          v-model="date3"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>


      <el-row :gutter="20" v-for="(checkInCustomer, index) in checkRecord.checkInCustomerList">
        <el-col :span="6">
          <el-form-item label="客户姓名" prop="customerName" required>
            <el-input v-model="checkInCustomer.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="手机号" prop="customerMobile" required>
            <el-input v-model="checkInCustomer.mobile"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="身份证号" prop="customerIdCard" required>
            <el-input v-model="checkInCustomer.idCard"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-button type="primary" v-on:click="addCheckInCustomer" v-if="index === checkRecord.checkInCustomerList.length - 1">添加入住人员</el-button>
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

  export default {
    data() {
      return {
        rooms: [],
        checkRecord: {
          room: {},
          checkInCustomerList: [{}],
          region: '',
          date1: '',
          date2: '',
          date3: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        rules: {
          name: [
            {required: true, message: '请输入活动名称', trigger: 'blur'},
            {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
          ],
          region: [
            {required: true, message: '请选择活动区域', trigger: 'change'}
          ],
          date1: [
            {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
          ],
          date2: [
            {type: 'date', required: true, message: '请选择时间', trigger: 'change'}
          ],
          type: [
            {type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change'}
          ],
          resource: [
            {required: true, message: '请选择活动资源', trigger: 'change'}
          ],
          desc: [
            {required: true, message: '请填写活动形式', trigger: 'blur'}
          ]
        }
      };
    },
    created() {
      // this.loadRoomTypes();
      this.loadRooms();
    },
    methods: {
      submitForm(formName) {
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
        RoomApi.findAll({}).then(response => {
          this.rooms = response.data;
        });
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
