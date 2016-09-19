require './src/test/e2e/support/helper'
require './src/test/e2e/support/client'

RSpec.describe "jeyson-java" do

  describe "e2e : xml" do

    before(:all) do
      @helper = Jeyson::TestHelper.new
      @client = Jeyson::Client.new(@helper.stub_root_url)
      @helper.start_server
    end

    after(:all) do
      @helper.stop_server
    end

    it "Should serve a plain json" do
      expected  = "<message>hello</message>"
      actual    = @client.get("/xml/hello")
      expect(actual).to eq(expected)
    end

  end
end
