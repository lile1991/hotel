import fetch from '@/utils/fetch'

let CheckRecordApi = {
  fullPage: function(path) {
    return "/checkRecord/" + path;
  },
  checkIn: function(data) {
    return fetch({
      url: this.fullPage('checkIn'),
      method: 'post',
      data: data
    });
  }
}

export default CheckRecordApi;
