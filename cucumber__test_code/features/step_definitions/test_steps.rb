Given(/^Open the homepage$/) do
  @homepage = HomePage.new
  @homepage.load
end

Given(/^Search "([^"]*)"$/) do | keyword |
  @homepage.search keyword
end

Given(/^Have (\d+) result$/) do | expect |
  expect(@homepage.result).to eq expect.to_i
end

Given(/^Add a mark$/) do	
  @homepage.execute_script("$('.addMarks').trigger('click')")
  sleep 1 
  @homepage.execute_script("$('#bookname').val('test2')") 
  @homepage.execute_script("$('#bookurl').val('http://www.baidu.com')")
  sleep 3
  @homepage.execute_script("$('#confirm').trigger('click')")
  sleep 2 
  @homepage.execute_script("$('.showMarks').trigger('click')")
end

