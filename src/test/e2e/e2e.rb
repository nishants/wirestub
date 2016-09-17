require 'service_mock'
require 'json'
require './src/test/e2e/helper'
require 'net/http'

RSpec.describe "jeyson-java" do

  describe "e2e" do

    before(:all) do
      @helper = Jeyson::TestHelper.new
      @server = ServiceMock::Server.new(@helper.path_to_jar)
      @server.start do |server|
        server.port             = 8081
        server.root_dir         = @helper.root_dir
        server.verbose          = true
        server.record_mappings  = false
        server.wait_for_process = true
        server.inherit_io       = false
      end
    end

    it "Setup" do
      expect("one").to eq("ones")
    end

    after(:all) do
      @server.stop
    end

  end
end
