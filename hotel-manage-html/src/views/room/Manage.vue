<template>
  <div class="room-container">
    <el-tabs :tab-position="tabPosition" v-loading="loadingRooms" @tab-click="fetchRoomManage">
      <el-tab-pane :label="roomType.name" v-for="roomType in roomTypes">
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
  import RoomTypeApi from '@/api/roomType'
  import RoomApi from '@/api/room'

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
        roomTypes: null,
        rooms: null,
        loadingRooms: false
      }
    },
    created() {
      this.fetchRoomType();
    },
    methods: {
      checkIn(room) {
        this.$router.push({ path: '/room/checkIn', room: room})
      },
      reserve(room) {
        alert("预定")
      },
      disable(room) {
        alert("停用")
      },
      fetchRoomType() {
        RoomTypeApi.findAll().then(response => {
          this.roomTypes = response.data;
          if(this.roomTypes && this.roomTypes.length > 0) {
            this.fetchRoomManage({index: 0});
          }
        });
      },
      fetchRoomManage(tab) {
        let roomType = this.roomTypes[tab.index];
        this.loadingRooms = true;
        RoomApi.findManage({roomType: {id: roomType.id}}).then(response => {
          this.rooms = response.data;
          this.loadingRooms = false;
        });
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
