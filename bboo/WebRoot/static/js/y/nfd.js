$(document).ready(
		function() {
			$('.J_del_nfd_data_detail').click(
					function() {
						p = $(this).attr('name');
						console.log(p);
						$.get('./nfd/nfd-handle!getDateDetail.do?id=' + p,
								function(data) {
									showAlert(data);
								});
					});

			$('#nfd_loads_id001_btn').click(
					function() {
						$('#nfd_loads_id001_btn').html('正在生成文件...');
						$('#aircode').val($.trim($('#aircode').val()));
						$('#cabincode').val($.trim($('#cabincode').val()));
						var aircode = $('#aircode').val();
						var cabincode = $('#cabincode').val();
						$.get('./nfd/nfd-handle!generateNfdFile.do?carrier='
								+ aircode + '&cabin=' + cabincode, function(
								data) {
							showAlert(data);
							$('#nfd_loads_id001_btn').html('生成文件结束');
						});
					});
		})