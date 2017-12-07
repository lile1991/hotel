import fetch from '@/utils/fetch'

let RoomtTypeApi = {
  fullPage: function(path) {
    return "/roomType/" + path;
  },
  findAll: function() {
    return fetch({
      url: fullPage('findAll'),
      method: 'get'
    });
  }
}

export default RoomtTypeApi;
