require 'childprocess'
require 'fileutils'

module Jeyson
  class TestHelper

    def initialize
      @path_to_jar    = File.expand_path("./")
      @jar_file_name  = "jeyson-1.0-SNAPSHOT-jar-with-dependencies.jar"
      @root_dir       = File.expand_path("./src/test/data/root-dir")
      @port           = 5135
      @server_log     = File.expand_path("./server.log")
      @server_timeout = 5
    end


    def stub_root_url()
      "http://localhost:#{@port}"
    end

    def start_server
      @process = ChildProcess.build("java", "-jar", @jar_file_name, "--port", @port.to_s, "--verbose", "--root-dir", @root_dir)
      @process.cwd = @path_to_jar

      FileUtils.touch(@server_log)
      out = File.new(@server_log, 'r+')
      @process.io.stdout = @process.io.stderr = out
      out.sync = true

      @process.duplex = true
      @process.leader = false
      @process.detach = true

      @process.start
      sleep @server_timeout
    end

    def stop_server
      begin
        @process.poll_for_exit(@server_timeout)
      rescue ChildProcess::TimeoutError
        @process.stop # tries increasingly harsher methods to kill the process.
      end
    end
  end
end
