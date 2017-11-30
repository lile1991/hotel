<template>
  <div class="room-container">
    <el-tabs :tab-position="tabPosition">
      <el-tab-pane :label="roomType" v-for="roomType in roomTypes">
        <el-row :gutter="20">
          <el-col :span="4" v-for="room in rooms">
            <el-card :body-style="{ padding: '0px' }" class="room-card">
              <span>{{room.number}}</span>
              <el-button-group class="room-card-option">
                <el-button type="primary" size="mini" icon="el-icon-search" v-on:click="checkIn(room)">入住</el-button>
                <el-button type="primary" size="mini" icon="el-icon-search" v-on:click="reserve(room)">预定</el-button>
                <el-button type="primary" size="mini" icon="el-icon-search" v-on:click="disable(room)">停用</el-button>
              </el-button-group>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    name: 'RoomManage',
    computed: {
      ...mapGetters([
        'name',
        'roles'
      ])
    },
    data() {
      return {
        tabPosition: "left",
        roomTypes: [
          "标准单间",
          "豪华单间",
          "标准双间",
          "豪华双间"
        ],
        rooms: [
          {
            number: "101",
            state: "入住"
          },
          {
            number: "102",
            state: "空置"
          }
        ]
      }
    },
    methods: {
      checkIn(room) {
        alert("入住" + room.number)
      },
      reserve(room) {
        alert("预定")
      },
      disable(room) {
        alert("停用")
      }
    }
  }

</script>

<style type="text/css">
  .room-container .room-card {
    background-image: url('http://element-cn.eleme.io/static/hamburger.50e4091.png');
    padding: 5px;
  }

  .room-card-option button {
    padding: 3px;
  }
</style>
