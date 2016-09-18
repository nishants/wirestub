require 'childprocess'

module Jeyson
  class TestHelper
    def path_to_jar
      File.expand_path "./"
    end

    def jar_file_name
      "jeyson-1.0-SNAPSHOT-jar-with-dependencies.jar"
    end

    def root_dir
      File.expand_path "src/test/data/root-dir"
    end

    def port
      5133
    end

    def server_log
      File.expand_path "server.log"
    end


    def stub_root_url()
      "http://localhost:#{port}"
    end

    def start_server
      @process = ChildProcess.build("java", "-jar", jar_file_name, "--port", port.to_s, "--verbose", "--root-dir", root_dir)
      @process.cwd = path_to_jar
      # @process.io.inherit!

      out = File.new(server_log, 'r+')
      @process.io.stdout = @process.io.stderr = out
      out.sync = true

      @process.duplex    = true # sets up pipe so process.io.stdin will be available after .start

      @process.leader = false
      @process.detach = true

      @process.start
      sleep 3
      # @process.wait
    end

    def stop_server
      begin
        @process.poll_for_exit(10)
      rescue ChildProcess::TimeoutError
        @process.stop # tries increasingly harsher methods to kill the process.
      end
    end
  end
end
