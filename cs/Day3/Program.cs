using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace Day3
{
    class Program
    {
        static void Main(string[] args)
        {
            List<string> lines = System.IO.File.ReadAllLines(args[0]).ToList();
            Day3 day3 = new Day3(lines);
            day3.Part1();
            day3.Part2();
        }

    }

    class Day3
    {
        private readonly List<string> lines;

        public Day3(List<string> lines)
        {
            this.lines = lines;
        }

        public void Part1()
        {
            var count = GenerateOverlapMap()
                .Cast<int>().ToList()
                .Aggregate(
                    0, 
                    (innertotal, innervalue) => innervalue > 1 ? innertotal + 1 : innertotal
                );
            Console.WriteLine(count);
        }

        public void Part2()
        {
            int[,] map = GenerateOverlapMap();
            int untouched = FindUntouchedClaim(map);
            Console.WriteLine(untouched);
        }

        private int FindUntouchedClaim(int[,] map)
        {
            int id = 0;
            foreach(var line in lines)
            {
                Claim rect = new Claim(line);
                void loop()
                {
                    foreach (int i in Enumerable.Range(0, rect.Width))
                    {
                        foreach (int j in Enumerable.Range(0, rect.Height))
                        {
                            if (map[rect.Position.Item1 + i, rect.Position.Item2 + j] > 1)
                            {
                                return;
                            }
                        }
                    }
                    id = rect.Id;
                }
                loop();
            }
            return id;
        }

        private int[,] GenerateOverlapMap()
        {
            int[,] map = new int[1000, 1000];
            foreach(var line in lines)
            {
                Claim rect = new Claim(line);
                foreach(int i in Enumerable.Range(0, rect.Width))
                {
                    foreach(int j in Enumerable.Range(0, rect.Height))
                    {
                        if ((map[rect.Position.x + i, rect.Position.y + j] += 1) > 1)
                        {

                        }
                    }
                }
            }
            return map;
        }
    }

    class Claim
    {
        public Claim(string line)
        {
            var matches = Regex.Match(line, @"^\#(\d+)\ \@\ (\d+)\,(\d+)\:\ (\d+)x(\d+)$");
            Id = int.Parse(matches.Groups[1].Value);
            Position = (int.Parse(matches.Groups[2].Value), int.Parse(matches.Groups[3].Value));
            Width = int.Parse(matches.Groups[4].Value);
            Height = int.Parse(matches.Groups[5].Value);
        }
        public int Id { get; }
        public (int x, int y) Position { get; }
        public int Width { get; }
        public int Height { get; }
    }
}
